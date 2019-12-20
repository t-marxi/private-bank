import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.klimigo.privatebank.server.dto.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Before run this test need to start application locally on url http://localhost:8080
 */
@Ignore
public class ScenarioTest {

    @Test
    public void test() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        Client client = Client.create();

        WebResource clientWR = client
                .resource("http://localhost:8080/client");

        // Create users
        ClientApplication clientApplication = new ClientApplication();
        clientApplication.setName("Igor");
        clientApplication.setSecondName("Olegovich");
        clientApplication.setLastName("Klimovskiy");
        io.klimigo.privatebank.server.dto.Client clientOne = getResponse(clientWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, clientApplication),
                io.klimigo.privatebank.server.dto.Client.class);
        System.out.println(String.format("Create a new client:\\r\\n%s", objectWriter.writeValueAsString(clientOne)));

        clientApplication.setName("Bill");
        clientApplication.setSecondName(null);
        clientApplication.setLastName("Gates");
        io.klimigo.privatebank.server.dto.Client clientTwo = getResponse(clientWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, clientApplication),
                io.klimigo.privatebank.server.dto.Client.class);
        System.out.println(String.format("Create a new client:\\r\\n%s", objectWriter.writeValueAsString(clientTwo)));

        // Create currencies
        WebResource currencyWR = client
                .resource("http://localhost:8080/currency");

        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setDescription("USD Dexcription");
        Currency currencyOne = getResponse(currencyWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, currency),
                Currency.class);
        System.out.println(String.format("Create a new currency:\\r\\n%s", objectWriter.writeValueAsString(currencyOne)));

        currency.setCode("GBP");
        currency.setDescription("GBP description");
        Currency currencyTwo = getResponse(currencyWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, currency),
                Currency.class);
        System.out.println(String.format("Create a new currency:\\r\\n%s", objectWriter.writeValueAsString(currencyTwo)));

        // create accounts
        WebResource accountWR = client
                .resource("http://localhost:8080/account");

        AccountApplication accountApplication = new AccountApplication();
        accountApplication.setClientId(clientOne.getId());
        accountApplication.setCurrencyId(currencyTwo.getId());
        accountApplication.setStartValue(BigDecimal.valueOf(120));
        Account accountOne = getResponse(accountWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, accountApplication),
                Account.class);
        System.out.println(String.format("Create a new account for the first client and in the second currency:\\r\\n%s",
                objectWriter.writeValueAsString(accountOne)));

        accountApplication.setClientId(clientTwo.getId());
        accountApplication.setCurrencyId(currencyOne.getId());
        accountApplication.setStartValue(BigDecimal.valueOf(1000000000));
        Account accountTwo = getResponse(accountWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, accountApplication),
                Account.class);
        System.out.println(String.format("Create a new account for the second client and in the first currency:\\r\\n%s",
                objectWriter.writeValueAsString(accountTwo)));

        // Create FxRates
        WebResource fxRateWR = client
                .resource("http://localhost:8080/fxrate");

        FxRateApplication fxRateApplication = new FxRateApplication();
        fxRateApplication.setCurrencyFrom(currencyTwo.getId());
        fxRateApplication.setCurrencyTo(currencyOne.getId());
        fxRateApplication.setValue(BigDecimal.valueOf(0.55));
        FxRate fxRateOne = getResponse(fxRateWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, fxRateApplication),
                FxRate.class);
        System.out.println(String.format("Create a new fxrate from the second currency into the first currency:\\r\\n%s",
                objectWriter.writeValueAsString(fxRateOne)));

        fxRateApplication.setCurrencyFrom(currencyTwo.getId());
        fxRateApplication.setCurrencyTo(currencyOne.getId());
        fxRateApplication.setValue(BigDecimal.valueOf(0.5));
        FxRate fxRateTwo = getResponse(fxRateWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, fxRateApplication),
                FxRate.class);
        System.out.println(String.format("Create a new fxrate from the second currency into the first currency (will be the newest):\\r\\n%s",
                objectWriter.writeValueAsString(fxRateTwo)));

        // Create transaction
        WebResource transactionWR = client
                .resource("http://localhost:8080/transaction");

        TransactionApplication transactionApplication = new TransactionApplication();
        transactionApplication.setAccountFrom(accountOne.getId());
        transactionApplication.setAccountTo(accountTwo.getId());
        transactionApplication.setValue(BigDecimal.valueOf(100));
        Transaction transaction = getResponse(transactionWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, transactionApplication),
                Transaction.class);
        System.out.println(String.format("Create a new transaction from the first account to the the second account:\\r\\n%s",
                objectWriter.writeValueAsString(transaction)));

        // Check balances
        accountOne = getResponse(accountWR.path(String.format("/%d", accountOne.getId()))
                        .accept("application/json")
                        .get(ClientResponse.class),
                Account.class);
        System.out.println(String.format("It's a new value of the first account after transaction:\\r\\n%s",
                objectWriter.writeValueAsString(accountOne)));
        Assert.assertEquals(clientOne.getId(), accountOne.getClient().getId());
        Assert.assertEquals(currencyTwo.getId(), accountOne.getCurrency().getId());
        Assert.assertEquals(20, accountOne.getValue().intValue());

        accountTwo = getResponse(accountWR.path(String.format("/%d", accountTwo.getId()))
                        .accept("application/json")
                        .get(ClientResponse.class),
                Account.class);
        System.out.println(String.format("It's a new value of the second account after transaction:\\r\\n%s",
                objectWriter.writeValueAsString(accountTwo)));
        Assert.assertEquals(clientTwo.getId(), accountTwo.getClient().getId());
        Assert.assertEquals(currencyOne.getId(), accountTwo.getCurrency().getId());
        Assert.assertEquals(1000000200, accountTwo.getValue().intValue());
    }

    private <T> T getResponse(ClientResponse clientResponse, Class<T> clazz) {
        if (clientResponse.getStatus() != 200) {
            Assert.assertFalse("Response status should be 200", true);
        }
        return clientResponse.getEntity(clazz);
    }
}

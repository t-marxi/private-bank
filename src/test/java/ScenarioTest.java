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
    public void test() {
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

        clientApplication.setName("Bill");
        clientApplication.setSecondName(null);
        clientApplication.setLastName("Gates");
        io.klimigo.privatebank.server.dto.Client clientTwo = getResponse(clientWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, clientApplication),
                io.klimigo.privatebank.server.dto.Client.class);

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


        currency.setCode("GBP");
        currency.setDescription("GBP description");
        Currency currencyTwo = getResponse(currencyWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, currency),
                Currency.class);

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

        accountApplication.setClientId(clientTwo.getId());
        accountApplication.setCurrencyId(currencyOne.getId());
        accountApplication.setStartValue(BigDecimal.valueOf(100000));
        Account accountTwo = getResponse(accountWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, accountApplication),
                Account.class);

        // Create FxRates
        WebResource fxRateWR = client
                .resource("http://localhost:8080/fxrate");

        FxRateApplication fxRateApplication = new FxRateApplication();
        fxRateApplication.setCurrencyFrom(currencyTwo.getId());
        fxRateApplication.setCurrencyTo(currencyOne.getId());
        fxRateApplication.setValue(BigDecimal.valueOf(1.5));
        FxRate fxRateOne = getResponse(fxRateWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, fxRateApplication),
                FxRate.class);

        fxRateApplication.setCurrencyFrom(currencyTwo.getId());
        fxRateApplication.setCurrencyTo(currencyOne.getId());
        fxRateApplication.setValue(BigDecimal.valueOf(2));
        FxRate fxRateTwo = getResponse(fxRateWR
                        .accept("application/json")
                        .type("application/json")
                        .post(ClientResponse.class, fxRateApplication),
                FxRate.class);

        // Create transaction
        /** TODO: Not work right now.
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
        */
        // Check balances

        // TODO:
    }

    private <T> T getResponse(ClientResponse clientResponse, Class<T> clazz) {
        if (clientResponse.getStatus() != 200) {
            Assert.assertFalse("Response status should be 200", true);
        }
        return clientResponse.getEntity(clazz);
    }
}

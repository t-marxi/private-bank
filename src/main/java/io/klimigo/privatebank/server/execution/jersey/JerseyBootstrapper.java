package io.klimigo.privatebank.server.execution.jersey;

import javax.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.servlet.GuiceFilter;
import io.klimigo.privatebank.server.execution.guice.InitializeGuiceModulesContextListener;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;
import utility.Globals;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JerseyBootstrapper {

    @Inject
    @Named("serverPort")
    int port;

    private Server jettyServer;

    public JerseyBootstrapper() {
        Globals.getGuiceInjector().injectMembers(this);
    }

    /**
     * Init jersey server from code instead of a web.xml
     */
    public void setupServer() {
        this.jettyServer = new Server(this.port);

        HandlerCollection handlerCollection = new HandlerCollection();

        // WebApp handler
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(this.jettyServer);

        // Guice filter
        webAppContext.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        ServletHolder holder = new ServletHolder(ServletContainer.class);
        holder.setInitParameter("javax.ws.rs.Application", JerseyConfiguration.class.getCanonicalName());
        holder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        holder.setInitParameter("jersey.config.server.provider.classnames", "org.glassfish.jersey.jackson.JacksonFeature");

        webAppContext.addServlet(holder, "/*");
        webAppContext.setResourceBase("/");
        webAppContext.setContextPath("/");
        webAppContext.addEventListener(new InitializeGuiceModulesContextListener());

        // Add all handlers
        handlerCollection.addHandler(webAppContext);

        // Set all handlers to jetty
        this.jettyServer.setHandler(handlerCollection);
    }

    public void startServer() throws Exception {
        this.jettyServer.start();
        this.jettyServer.join();
    }

    public void stopServer() throws Exception {
        this.jettyServer.stop();
    }

    public void destroyServer() {
        this.jettyServer.destroy();
    }
}

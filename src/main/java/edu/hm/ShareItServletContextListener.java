package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.shareit.service.MediaService;
import edu.hm.shareit.service.MediaServiceImpl;

/**
 * Context Listener to enable usage of google guice together with jersey.
 */
public class ShareItServletContextListener extends GuiceServletContextListener {

    private static final Injector injector = Guice.createInjector(new ServletModule() {

        @Override
        protected void configureServlets() {
            bind(MediaService.class).to(MediaServiceImpl.class);
        }
    });

    /**
     * This method is only required for the HK2-Guice-Bridge in the
     * Application class.
     *
     * @return Injector instance.
     */
    static Injector getInjectorInstance() {
        return injector;
    }

    @Override
    protected Injector getInjector() {
        return injector;
    }
}
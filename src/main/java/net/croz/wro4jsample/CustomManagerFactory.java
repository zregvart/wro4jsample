package net.croz.wro4jsample;

import com.google.javascript.jscomp.CompilationLevel;

import ro.isdc.wro.extensions.locator.WebjarUriLocator;
import ro.isdc.wro.extensions.processor.css.Less4jProcessor;
import ro.isdc.wro.extensions.processor.css.YUICssCompressorProcessor;
import ro.isdc.wro.extensions.processor.js.GoogleClosureCompressorProcessor;
import ro.isdc.wro.manager.factory.standalone.DefaultStandaloneContextAwareManagerFactory;
import ro.isdc.wro.model.resource.locator.factory.SimpleUriLocatorFactory;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.processor.decorator.CopyrightKeeperProcessorDecorator;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;

public class CustomManagerFactory extends DefaultStandaloneContextAwareManagerFactory {
    @Override
    protected ProcessorsFactory newProcessorsFactory() {
        final SimpleProcessorsFactory factory = new SimpleProcessorsFactory();
        factory.addPostProcessor(new Less4jProcessor());
        factory.addPostProcessor(CopyrightKeeperProcessorDecorator.decorate(new GoogleClosureCompressorProcessor(CompilationLevel.SIMPLE_OPTIMIZATIONS)));
        factory.addPostProcessor(new YUICssCompressorProcessor());

        return factory;
    }

    @Override
    protected UriLocatorFactory newUriLocatorFactory() {
        return new SimpleUriLocatorFactory()//
                .addLocator(newServletContextUriLocator())//
                .addLocator(new WebjarUriLocator());
    }
}

package com.w6.config;

import com.google.code.geocoder.Geocoder;
import com.w6.external_api.Geolocator;
import com.w6.nlp.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = {"com.w6"})
@EnableWebMvc
@EnableScheduling
@ImportResource("classpath:applicationContext.xml")
public class AppContext {

    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

        return viewResolver;
    }


    @Bean
    public MySolrClient mySolrClient() {
        return new MySolrClient();
    }


    @Bean
    public LexicalizedParser lp() {
        return LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
    }

    @Bean
    public ViolentVerbsParser violentVerbsParser() throws IOException {
        return new ViolentVerbsParser(lp());
    }

    @Bean
    public WeaponsParser weaponsParser() throws IOException {
        return new WeaponsParser(lp());
    }

    @Bean
    public CountryParser countryParser() throws IOException {
        return new CountryParser();
    }

    @Bean
    public TokenizerFactory<CoreLabel> tokenizerFactory() {
        return PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
    }

    @Bean
    public Parser parser() throws IOException {
        return new Parser();
    }
    
    @Bean
    public Geolocator geolator()
    {
        return new Geolocator();
    }

    @Bean
    public Geocoder geocoder()
    {
        return new Geocoder();
    }
    
    @Bean
    public EventGuesser eventGuesser()
    {
        return new EventGuesser();
    }
}

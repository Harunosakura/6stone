package com.game.kalah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
/**
 * To deploy the spring boot "WEB" on external application server <br> (not the
 * embedded one) this need 3 steps <br>
 * <ul>
 * <li> in pom.xml change line  <packaging>jar</packaging> to
 * <packaging>war</packaging> </li>
 * <li>Application main class should extend SpringBootServletInitializer<br>
 * <code>public class KalahApplication extends SpringBootServletInitializer{
 * </code></li>
 * <li>add the below method into main class, overrides the configure()
 * method.<br>That method uses SpringApplicationBuilder to simply register our
 * class as a configuration class of the application. </li>
 * <code>
 *
 * @Override protected SpringApplicationBuilder configure(
 * SpringApplicationBuilder builder) { return
 * builder.sources(KalahApplication.class); }
 * </code>
 * </ul>
 */
public class KalahApplication extends SpringBootServletInitializer {

         /**
          * When running as a JAR main will be called
          *
          * @param args
          */
         public static void main(String[] args) {
                  SpringApplication.run(KalahApplication.class, args);
         }

         /**
          * When running as a WAR  this will be called
          *
          * @param builder
          * @return
          */
         @Override
         protected SpringApplicationBuilder configure(
                 SpringApplicationBuilder builder) {
                  return builder.sources(KalahApplication.class);
         }
}

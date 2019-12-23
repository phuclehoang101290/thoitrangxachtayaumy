/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 * 
 * package guru.springframework;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import
 * org.springframework.security.config.annotation.web.builders.WebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.web.authentication.logout.
 * HttpStatusReturningLogoutSuccessHandler; import
 * org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 * 
 * import guru.springframework.services.CustomUserDetailsService;
 * 
 *//**
	 *
	 * @author didin
	 *//*
		 * @Configuration
		 * 
		 * @EnableWebSecurity public class WebSecurityConfig extends
		 * WebSecurityConfigurerAdapter {
		 * 
		 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
		 * 
		 * @Autowired CustomizeAuthenticationSuccessHandler
		 * customizeAuthenticationSuccessHandler;
		 * 
		 * @Bean public UserDetailsService mongoUserDetails() { return new
		 * CustomUserDetailsService(); }
		 * 
		 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
		 * Exception { UserDetailsService userDetailsService = mongoUserDetails(); auth
		 * .userDetailsService(userDetailsService)
		 * .passwordEncoder(bCryptPasswordEncoder);
		 * 
		 * }
		 * 
		 * @Override protected void configure(HttpSecurity http) throws Exception { http
		 * .authorizeRequests() .antMatchers("/").permitAll() .antMatchers("/page/{id}",
		 * "/news-detail/{id}", "/contact", "/advisory", "/add-advisory",
		 * "/register-email/{email}", "/send-message").permitAll()
		 * .antMatchers("/resources/**").permitAll() .antMatchers("/sensive/**",
		 * "/startbootstrap-sb-admin-gh-pages/**").permitAll()
		 * .antMatchers("/img/**").permitAll() .antMatchers("/css/**").permitAll()
		 * .antMatchers("/js/**").permitAll() .antMatchers("/login").permitAll()
		 * .antMatchers("/signup").permitAll()
		 * .antMatchers("/dashboard/**").hasAuthority("ADMIN").anyRequest()
		 * .authenticated().and().csrf().disable().formLogin().successHandler(
		 * customizeAuthenticationSuccessHandler)
		 * .loginPage("/login").failureUrl("/login?error=true")
		 * .usernameParameter("email") .passwordParameter("password") .and().logout()
		 * .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 * .logoutSuccessUrl("/login").and().exceptionHandling(); http.rememberMe(); //
		 * Customize the application security
		 * //http.requiresChannel().anyRequest().requiresSecure(); }
		 * 
		 * @Override public void configure(WebSecurity web) throws Exception { web
		 * .ignoring() .antMatchers(HttpMethod.OPTIONS, "/resources/**", "/static/**",
		 * "/css/**", "/js/**", "/img/**", "/startbootstrap-sb-admin-gh-pages/**",
		 * "/sensive/**"); }
		 * 
		 * }
		 */
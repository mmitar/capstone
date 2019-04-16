package test.resources;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.layers.app.business.ActivityBusinessService;
import com.layers.app.business.LiquorBusinessService;
import com.layers.app.business.LocationBusinessService;
import com.layers.app.business.ScaleBusinessService;
import com.layers.app.business.UserBusinessService;
import com.layers.app.data.ActivityDataService;
import com.layers.app.data.LiquorDataService;
import com.layers.app.data.LocationDataService;
import com.layers.app.data.ScaleDataService;
import com.layers.app.data.UserDataService;

@Configuration
public class junitConfiguration 
{
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //MySQL database we are using
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdream");//change url
        dataSource.setUsername("root");//change userid
        dataSource.setPassword("root");//change pwd
        return dataSource;
    }

    @Bean
    public UserBusinessService userBusinessService()
    {
    	return new UserBusinessService();
    }
    
    @Bean
    public LiquorBusinessService liquorBusinessService()
    {
    	return new LiquorBusinessService();
    }
 
    @Bean
    public ScaleBusinessService scaleBusinessService()
    {
    	return new ScaleBusinessService();
    }
    
    @Bean
    public LocationBusinessService locationBusinessService()
    {
    	return new LocationBusinessService();
    }
    
    @Bean
    public ActivityBusinessService activityBusinessService()
    {
    	return new ActivityBusinessService();
    }

    @Bean
    public UserDataService userDataService()
    {
    	UserDataService uds = new UserDataService();
    	uds.setDataSource(dataSource());
        return uds;
    }
    
    @Bean
    public ActivityDataService activityDataService()
    {
    	ActivityDataService ads = new ActivityDataService();
    	ads.setDataSource(dataSource());
        return ads;
    }
    
    @Bean
    public LocationDataService locationDataService()
    {
    	LocationDataService lds = new LocationDataService();
    	lds.setDataSource(dataSource());
        return lds;
    }
    
    @Bean
    public LiquorDataService liquorDataService()
    {
        LiquorDataService lds = new LiquorDataService();
        lds.setDataSource(dataSource());
        return lds;
    }
    
    @Bean
    public ScaleDataService scaleDataService()
    {
    	ScaleDataService sds = new ScaleDataService();
    	sds.setDataSource(dataSource());
        return sds;
    }
}
 

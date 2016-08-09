package home.training.jdk8.lambda;

import home.training.jdk8.lambda.robocall.RoboContact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by renrodriguez on 5/9/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LambdaApplicationTest {

    @Autowired
    private RoboContact robo;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(robo);
    }

}

package com.org.project.automation.runners;


import com.org.project.automation.listeners.CustomJUnitListener;
import cucumber.api.junit.Cucumber;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class adds custom Junit Listeners with Cucumber
 */
public class CucumberCustomRunner extends Cucumber {
private static final Logger LOGGER= LoggerFactory.getLogger(CucumberCustomRunner.class);

/**
 * Consturctor called by Junit.
 *
 * @param clazz the class with the @RunWith annotation
 * @throws InitializationError if there is another problem
 */

public CucumberCustomRunner(Class clazz) throws InitializationError{
    super(clazz);
}

/**
 * This method adds custom Junit Listeners
 *
 * @param notifier
 */
@Override
    public void run(final RunNotifier notifier){
    LOGGER.info("Adding Junit custom Listeners");
    notifier.addListener(new CustomJUnitListener());
    super.run(notifier);
}

}

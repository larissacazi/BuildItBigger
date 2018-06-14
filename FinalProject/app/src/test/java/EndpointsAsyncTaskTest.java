import com.udacity.gradle.builditbigger.AsyncResponse;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;


public class EndpointsAsyncTaskTest implements AsyncResponse {

    @Test
    public void testVerifyJoke() throws InterruptedException, ExecutionException {
        /* CountDownLatch latch = new CountDownLatch(1);
        final EndpointsAsyncTask testTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (result != null){
                    assertTrue(result.length() > 0);
                    latch.countDown();
                }
            }
        };

        testTask.execute();


        latch.await();*/

        EndpointsAsyncTask testTask = new EndpointsAsyncTask(this);
    }

    @Override
    public void processFinish(String output) {
        assertNotNull(output);
        if (output != null){
            assertTrue(output.length() > 0);
        }
    }
}
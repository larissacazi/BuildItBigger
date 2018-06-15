import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;


public class EndpointsAsyncTaskTest implements AsyncResponse {

    @Test
    public void testVerifyJoke() throws InterruptedException, ExecutionException {
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
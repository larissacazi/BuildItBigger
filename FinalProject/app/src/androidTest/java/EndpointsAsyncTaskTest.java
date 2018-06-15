import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import com.udacity.gradle.builditbigger.AsyncResponse;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/*
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements AsyncResponse {
    private CountDownLatch latch;

    @Test
    public void testVerifyJoke() throws InterruptedException, ExecutionException {
        latch = new CountDownLatch(1);
        EndpointsAsyncTask testTask = new EndpointsAsyncTask(this);
        testTask.execute();
        latch.await();
    }

    @Override
    public void processFinish(String output) {
        assertNotNull(output);
        if (output != null){
            assertTrue(output.length() > 0);
            latch.countDown();
        }
    }
}*/

public class EndpointsAsyncTaskTest extends AndroidTestCase implements AsyncResponse{
    private static final String TAG = "EndpointsAsyncTaskTest";

    @SuppressWarnings("unchecked")
    public void testVerifyJoke() {

        Log.v(TAG, "Running EndpointsAsyncTaskTest test");
        String result = null;
        EndpointsAsyncTask testTask = new EndpointsAsyncTask(this);
        testTask.execute();
        try {
            result = testTask.get();
            Log.d(TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

    @Override
    public void processFinish(String output) {

    }
}

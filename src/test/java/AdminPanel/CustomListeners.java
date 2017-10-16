package AdminPanel;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements IInvokedMethodListener, ITestListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("beforeInvocation: " + testResult.getTestClass().getName() + " ---> " + method.getTestMethod().getMethodName());
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("afterInvocation: " + testResult.getTestClass().getName() + " ---> " + method.getTestMethod().getMethodName());
		
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart --> Test name: " + result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess --> Test name: " + result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure --> Test name: " + result.getName());
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped --> Test name: " + result.getName());
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		System.out.println("onStart --> Test Tag name: " + context.getName());
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("onFinish --> Test Tag name: " + context.getName());
		
	}

}

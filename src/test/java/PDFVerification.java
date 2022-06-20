import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;

public class PDFVerification {
	private static BatchInfo batch;
    private EyesRunner runner;
    private Eyes eyes;
    WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		 batch=new BatchInfo("Demo PDF file");
		 
		 runner=new ClassicRunner();
         Eyes eyes= new Eyes(runner);
		 
		 eyes.setApiKey("my2100ii3aHg6VvqJv699Mc0bcVGqjD3rhpTWk6WiDmhOA110");
		 
		 eyes.setBatch(batch);
		 
		
	}
	public boolean validatePDF(String filepath) throws IOException, InterruptedException {
		ProcessBuilder pb= new ProcessBuilder(
				"java",
				"-jar",
				"src/test/resources/Libraby/ImageTester_2.3.5.jar",
				"-a",
				"PDF Verification",
				"-k",
				"my2100ii3aHg6VvqJv699Mc0bcVGqjD3rhpTWk6WiDmhOA110",
				"-f",
				filepath);
		pb.redirectErrorStream(true);
		Process process=pb.start();
		process.waitFor();
		String stream= IOUtils.toString(process.getInputStream(),"UTF-8");
		
		if(stream !=null && (stream.contains("Mismatch")|| stream.contains("Unresolved"))) {
			return false;
		}
		return true;
	}
	
	@Test
	public void testPDFValidation() throws IOException, InterruptedException {
		
		String pdfFile="src/test/resources/PDFfiles";
		Assert.assertTrue(validatePDF(pdfFile),"Error Validating PDF");
		
	}
	
	@AfterClass
	public void afterClass() {
		eyes.abortIfNotClosed();
	}

}

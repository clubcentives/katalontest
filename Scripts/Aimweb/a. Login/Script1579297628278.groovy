import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

if (!(IN_USER_ID) || !(IN_PASSWORD)) {
    throw new com.kms.katalon.core.exception.StepFailedException('LoginAs: Missing required parameter value. Either user id or password is blank. They are required.')
}

CustomKeywords.'mya.Lib.openURL'(GlobalVariable.GLOBAL_URL_MYA + '/account/login?from=')

WebUI.deleteAllCookies()

WebUI.setText(findTestObject('myAesculap Login/User ID Field'), IN_USER_ID)

WebUI.setText(findTestObject('myAesculap Login/Password Field'), IN_PASSWORD)

WebUI.click(findTestObject('myAesculap Login/Login Button'))

RETURN_USER_NAME = WebUI.getText(findTestObject('myAesculap Home/Header User Name'))

WebUI.comment('Successfully logged in as user' + OUT_USER_NAME)

WebUI.executeJavaScript('Application.showMessage("hi there!", \'success\', false); Application.showMessage("hi there 2!", \'success\', false)', 
    [])

WebUI.verifyElementVisible(findTestObject('_All/App Search Field'))

WebUI.comment('This is a test comment')


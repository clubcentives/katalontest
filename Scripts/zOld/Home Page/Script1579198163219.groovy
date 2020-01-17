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

CustomKeywords.'mya.Lib.openURL'(GlobalVariable.GLOBAL_URL_AIMWEB)

WebUI.click(findTestObject('Object Repository/AIMWeb Home Page/Tab - Recent Quotes'))

WebUI.waitForElementVisible(findTestObject('AIMWeb Home Page/Filter - ID'), 8)

WebUI.verifyElementVisible(findTestObject('AIMWeb Home Page/Filter - ID'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('AIMWeb Home Page/Message - Any Error Message'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/AIMWeb Home Page/Filter - ID'), 'a')

WebUI.setText(findTestObject('Object Repository/AIMWeb Home Page/Filter - Name'), 'a')

WebUI.setText(findTestObject('Object Repository/AIMWeb Home Page/Filter - Customer Name'), 'a')

WebUI.selectOptionByValue(findTestObject('Object Repository/AIMWeb Home Page/Filter - Status'), 'draft', true)

WebUI.setText(findTestObject('Object Repository/AIMWeb Home Page/Filter - Grand Total'), '3')

WebUI.setText(findTestObject('Object Repository/AIMWeb Home Page/Filter - Created On'), '33')

WebUI.setText(findTestObject('Object Repository/AIMWeb Home Page/Filter - Expires on'), '333')

WebUI.sendKeys(findTestObject('Object Repository/AIMWeb Home Page/Filter - Expires on'), Keys.chord(Keys.ENTER))

WebUI.verifyElementNotPresent(findTestObject('AIMWeb Home Page/Message - Any Error Message'), 5, FailureHandling.STOP_ON_FAILURE)


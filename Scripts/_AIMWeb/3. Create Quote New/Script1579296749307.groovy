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

WebUI.comment('Setup')

CustomKeywords.'mya.Lib.openURL'(GlobalVariable.GLOBAL_URL_AIMWEB + '/quote')

WebUI.comment('Header')

WebUI.setText(findTestObject('Object Repository/AIMWeb Quote Page/QuoteNameField'), IN_QUOTE_NAME)

WebUI.selectOptionByValue(findTestObject('Object Repository/AIMWeb Quote Page/TerritorySelect'), IN_TERRITORY_NUMBER, true)

WebUI.setText(findTestObject('Object Repository/AIMWeb Quote Page/CustomerNameField'), IN_CUSTOMER_ID)

WebUI.delay(2)

WebUI.click(findTestObject('AIMWeb Quote Page/Customer20070012Link'))

WebUI.delay(2)

WebUI.selectOptionByIndex(findTestObject('Object Repository/AIMWeb Quote Page/CustomerContactField'), 2, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/AIMWeb Quote Page/DiscountAmountField'), IN_DISCOUNT_AMOUNT)

WebUI.setText(findTestObject('Object Repository/AIMWeb Quote Page/DiscountReasonField'), IN_DISCOUNT_REASON)

WebUI.comment('Details')

WebUI.executeJavaScript('App.activeTabset().removeAllTabs()', [])

for (def row = 1; row <= IN_TAB_NAMES.getRowNumbers(); row++) {
    WebUI.click(findTestObject('AIMWeb Quote Page/AddNewTabIcon'))

    WebUI.setText(findTestObject('AIMWeb Quote Page/AlertNewTabNameField'), IN_TAB_NAMES.getValue('Name', row))

    WebUI.click(findTestObject('AIMWeb Quote Page/button_OK (1)'))

    WebUI.delay(1)
}

int tabIndex = 0

for (tabIndex = 1; tabIndex <= IN_TAB_NAMES.getRowNumbers(); tabIndex++) {
    WebUI.executeJavaScript(('$(\'#quote-content-tabs .ui-tab:eq(' + (tabIndex - 1).toString()) + ')\').click()', [])

    WebUI.delay(1)

    def tabData = findTestData(IN_TAB_DATA_PREFIX + tabIndex)

    int rowIndex = 0

    for (rowIndex = 1; rowIndex <= tabData.getRowNumbers(); rowIndex++) {
        WebUI.executeJavaScript(('$(\'.jqgrow:visible:eq(' + (rowIndex - 1).toString()) + ') .cell-quantity\').click()', 
            [])

        WebUI.delay(1)

        WebUI.setText(findTestObject('AIMWeb Quote Page/ActiveRow_QuantityField'), tabData.getValue('Qty', rowIndex))

        WebUI.setText(findTestObject('AIMWeb Quote Page/ActiveRow_ManufacturerField'), tabData.getValue('Manufacturer', 
                rowIndex))

        WebUI.setText(findTestObject('AIMWeb Quote Page/ActiveRow_SkuField'), tabData.getValue('Sku', rowIndex))

        WebUI.setText(findTestObject('AIMWeb Quote Page/ActiveRow_XrefField'), '')

        WebUI.setText(findTestObject('AIMWeb Quote Page/ActiveRow_DescriptionField'), tabData.getValue('Description', rowIndex))

        WebUI.sendKeys(findTestObject('AIMWeb Quote Page/ActiveRow_DescriptionField'), Keys.chord(Keys.ENTER))
    }
    
    WebUI.executeJavaScript('$(\'#content-body\').click()', [])
}

WebUI.delay(3)

WebUI.click(findTestObject('AIMWeb Quote Page/ButtonSaveQuote'))

WebUI.waitForJQueryLoad(10)

not_run: WebUI.verifyElementPresent(findTestObject('Page_AIMWeb Online Quoting System - CreateE_f660ca/QuoteIDField'), 8)

not_run: OUT_QUOTE_ID = WebUI.getText(findTestObject('Object Repository/Page_AIMWeb Online Quoting System - CreateE_f660ca/QuoteIDField'))

not_run: assert OUT_QUOTE_ID != ''

OUT_QUOTE_ID = WebUI.executeJavaScript('return $(\'#form-main #id\').val()', [])

WebUI.comment('Quote created is ' + OUT_QUOTE_ID)


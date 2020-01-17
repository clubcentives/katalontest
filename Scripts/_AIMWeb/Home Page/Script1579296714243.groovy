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

CustomKeywords.'mya.Lib.openURL'(GlobalVariable.GLOBAL_URL_AIMWEB)

WebUI.waitForPageLoad(10)

WebUI.executeJavaScript('\n\n\t// config\n\tvar gridLoadWaitSeconds = 4;\n\t\n\t// mark the test as in progress and prep a callback to mark the test as having been done\n\t$(\'#gridcheckdoneflag\').remove();\n\tvar done = function(){\n\t  $(\'body\').append(\'<div id="scriptdone">&nbsp;</div>\'); // NOTE: due to a bug the div MUST have content to be detectable by the Exists check\n\t};\n\t\n\t// reset all stored filters of an grids currently showing\n\tCache.clearAll();\n\tApp.resetAndReloadAllGrids();\n\t\n\t// get references we\'ll need\n\tvar $tabs = $(\'.ui-tabs-nav a\', \'#home-content-tabs\');\n\t\n\t// click on all the tabs to load all grids\n\t$tabs.click();\n\t\n\tvar numTabsWithoutGrids = 1;\n\tvar numTabsEqualsNumGridsExpression = "$(\'.ui-jqgrid\', \'#home-content-tabs\').length + "+numTabsWithoutGrids+" == $(\'.ui-tabs-nav a\', \'#home-content-tabs\').length";\n\tvar noGridLoadinMessagesExpression = "$(\'.loading:visible\', \'.ui-jqgrid\').length==0";\n\t\n\twaitUntil(numTabsEqualsNumGridsExpression, function(){\n\t\n\t\tvar tabs = [];\n\t\t$(\'.ui-tabs-nav a\', container).each(function(){\n\t\t\ttabs.push($(this));\n\t\t});\n\t\n\t\tvar processTab = function($tab){\n\t\t\tApp.hideLoadingOverlay();  // in case any is visible\n\t\t\tconsole.log(\'processTab called\');\n\t\t\t$tab.click();\n\t\t\tvar tabHref = $tab.attr(\'href\');\n\t\t\tvar $tabDiv = $(tabHref);\n\t\t\tvar $grid = $(\'.ui-jqgrid\', $tabDiv);\n\t\t\tif ($grid.length) {\n\t\t\t\tconsole.log(\'installing filters for grid\'+$grid.attr(\'id\'));\n\t\t\t\t$(\'.ui-search-input select\', $grid).prop("selectedIndex", 1);\n\t\t\t\t$(\'.ui-search-input input\', $grid).val(\'x\');\n\t\t\t\t$(\'.ui-search-input input:first\', $grid).pressEnter();\n\t\t\t\t$tab.attr(\'data-processed\', 1);\n\t\t\t}\n\t\t\telse {\n\t\t\t\tconsole.log(\'Skipping tab \'+tabHref+\' because no grid found on the tab\');\n\t\t\t}\n\t\t\tif (tabs.length > 0) {\n\t\t\t\tsetTimeout(function(){\n\t\t\t\t\tprocessTab(tabs.pop());\n\t\t\t\t}, gridLoadWaitSeconds*1000);\n\t\t\t}\n\telse {\n\t  waitUntil("$(\'.loading:visible\', \'.ui-jqgrid\').length==0", done);\n\t}\n\t\t};\n\t\n\t\tprocessTab(tabs.pop());\n\t});\n\n', 
    [])

WebUI.waitForElementPresent(findTestObject('_All/ScriptDoneFlag'), 90)

WebUI.comment('Element appears to have been found')


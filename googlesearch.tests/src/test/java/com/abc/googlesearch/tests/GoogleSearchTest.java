package com.abc.googlesearch.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abc.googlesearch.pageobjects.GoogleSearchPage;
import com.abc.googlesearch.pageobjects.GoogleSearchResultsPage;
import com.abc.googlesearch.pageobjects.NavigatedWebPage;

import utils.ExcelUtils;
import utils.Log;

/**
 * 
 * @author Thusitha Bandara
 * @date 20/08/2019
 * 
 *
 */

public class GoogleSearchTest extends BaseTest {

	/**
	 * 
	 * @throws Exception 
	 * set the excel sheet
	 * 
	 */
	@BeforeTest
	public void setupTestData() throws Exception {
		try {
			ExcelUtils.setExcelFileSheet("Sheet1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws Exception
	 * google search test
	 * 
	 */
	@Test
	public static void googleSearch() throws Exception {

		try {
			GoogleSearchPage googleSearch = new GoogleSearchPage(driver);

			Log.info("Trying to perform google search" + ExcelUtils.getCellData(1, 0));
			googleSearch.typeSearchText(ExcelUtils.getCellData(1, 0));

			GoogleSearchResultsPage googleResults = googleSearch.clickSearchButton();

			Log.info("Trying to navigate to page" + ExcelUtils.getCellData(1, 1));
			NavigatedWebPage navigatedPage = googleResults
					.clickOnResult(Integer.parseInt(ExcelUtils.getCellData(1, 1)));

			Log.info("Verifying navigation to the correct page" + ExcelUtils.getCellData(1, 2));
			Assert.assertEquals(navigatedPage.getPageTitle(), ExcelUtils.getCellData(1, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

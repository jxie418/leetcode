package com.test.mapping;

import junit.framework.Assert;

import org.junit.Test;

public class MappingUtilityTest {

	@Test
	public void test_MappedValueFromTask() {

		MappingUtility mappingUtility = new MappingUtility();
		Assert.assertEquals("TEST-FB",
				mappingUtility.mappedValueFromTask("Claim_Nbr"));
		Assert.assertEquals("San Diego",
				mappingUtility.mappedValueFromTask("CITY"));
		Assert.assertEquals("CA", mappingUtility.mappedValueFromTask("STATE"));
		Assert.assertEquals("92128-", mappingUtility.mappedValueFromTask("ZIP"));
		Assert.assertEquals("(858)794-6134",
				mappingUtility.mappedValueFromTask("PHONE_NUMBER"));
		Assert.assertEquals("SHPFA86",
				mappingUtility.mappedValueFromTask("CLIENT_ID"));
		Assert.assertEquals("Compliance Shop 86",
				mappingUtility.mappedValueFromTask("Shop_Name"));
		  
		Assert.assertNotNull(mappingUtility.mappedValueFromTask("NON_OEM_AMT"));
	}

	@Test
	public void test_getMappedValueFromTask_Scorecard1() {

		MappingUtility mappingUtility = new MappingUtility("Scorecard1");
		Assert.assertEquals("TEST-FB",
				mappingUtility.getMappedValueFromTask("Claim_Nbr"));
		Assert.assertEquals("San Diego",
				mappingUtility.getMappedValueFromTask("CITY"));
		Assert.assertEquals("CA",
				mappingUtility.getMappedValueFromTask("STATE"));
		Assert.assertEquals("92128-",
				mappingUtility.getMappedValueFromTask("ZIP"));
		Assert.assertEquals("(858)794-6134",
				mappingUtility.getMappedValueFromTask("PHONE_NUMBER"));
		Assert.assertEquals("SHPFA86",
				mappingUtility.getMappedValueFromTask("CLIENT_ID"));
		Assert.assertEquals("Compliance Shop 86",
				mappingUtility.getMappedValueFromTask("Shop_Name"));

	}

}

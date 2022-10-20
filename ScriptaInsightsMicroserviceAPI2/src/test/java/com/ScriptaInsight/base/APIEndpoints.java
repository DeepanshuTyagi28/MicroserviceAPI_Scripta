package com.ScriptaInsight.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class APIEndpoints {

	public static String AuthorizationApiURI(String[] args) {
		String AuthorizationApiURI = "https://member-app-services-int-pipeline.scriptainsights.io/scripta-insights/member-app-api/0.1/member/authenticate/";
		return AuthorizationApiURI;

	}

	public static String DiscontinueDrugURI(String[] args) {
		String DiscontinueDrugURI = "https://member-blue.scriptainsights.io/public/v1/members/62.3828/configurations/discontinuedPrescriptionDrugs";
		return DiscontinueDrugURI;

	}

	public static String GetDrugNDCListBYDrugId(String[] args) {
		String GetDrugNDCListBYDrugId = "https://catalog-blue.scriptainsights.io/public/v1/catalog/drugs";
		return GetDrugNDCListBYDrugId;

	}

		}



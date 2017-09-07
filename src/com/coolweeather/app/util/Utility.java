package com.coolweeather.app.util;

import com.coolweeather.app.db.CoolWeatherDB;
import com.coolweeather.app.model.City;
import com.coolweeather.app.model.Country;
import com.coolweeather.app.model.Province;

import android.R.bool;
import android.text.TextUtils;

public class Utility {
	/*
	 * 解析和出来服务器返回省级数据
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String  response){
		if(!TextUtils.isEmpty(response)){
			System.out.println("response================"+response);
			String[] allProvinces = response.split(",");
			if(allProvinces!=null && allProvinces.length>0){
				for(String p:allProvinces){
					String[] array = p.split("\\|");
					Province province = new Province();
					System.out.println("array[0]++++++"+array[0]);
					System.out.println("array[1]++++++"+array[1]);
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//将解析出来的数据存储到Province表中
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * 解析和出来服务器返回市级数据
	 */
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String  response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities = response.split(",");
			if(allCities!=null && allCities.length>0){
				for(String c:allCities){
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityName(array[1]);
					city.setCityCode(array[0]);
					city.setProvinceId(provinceId);
					//将解析出来的数据存储到Province表中
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 解析和出来服务器返回县级数据
	 */
	public synchronized static boolean handleCountriesResponse(CoolWeatherDB coolWeatherDB,String  response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCountries = response.split(",");
			if(allCountries!=null && allCountries.length>0){
				for(String c:allCountries){
					String[] array = c.split("\\|");
					Country country = new Country();
					country.setCountryName(array[1]);
					country.setCountryCode(array[0]);
					country.setCityId(cityId);
					//将解析出来的数据存储到Province表中
					coolWeatherDB.saveCountry(country);
				}
				return true;
			}
		}
		return false;
	}
	
}


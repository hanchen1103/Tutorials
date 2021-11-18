package com.demo.newproject.service;

import com.demo.newproject.model.Weather;
import com.demo.newproject.util.JedisAdapter;
import com.demo.newproject.util.pythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class WeatherService {

    @Autowired
    JedisAdapter jedisAdapter;

    public Weather getWeatherInfo(String cityName) throws IOException, InterruptedException {
        pythonUtil.loadScript(cityName);
        Weather weather = new Weather();
        weather.setHumidity(jedisAdapter.hget(cityName, "shidu"));
        weather.setRecordTime(jedisAdapter.hget(cityName, "updatetime"));
        weather.setSunDown(jedisAdapter.hget(cityName, "sunset_1"));
        weather.setSunRise(jedisAdapter.hget(cityName, "sunrise_1"));
        weather.setWindPower(jedisAdapter.hget(cityName, "fengli"));
        weather.setTemperature(jedisAdapter.hget(cityName, "wendu"));
        weather.setWindFor(jedisAdapter.hget(cityName, "fengxiang"));
        weather.setCityName(jedisAdapter.hget(cityName, "city"));
        return weather;
    }
}

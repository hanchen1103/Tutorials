import sys
import requests
from lxml import etree
import redis

r = redis.StrictRedis(host = '127.0.0.1', port = 6379, db = 1)

map = {}

def request_url(url):
    response = requests.request('get', url)
    return response.text

def explain_requet(response):
    global city
    t = etree.XML(bytes(bytearray(response, 'utf-8')))
    tree = etree.ElementTree(t)
    root = tree.getroot()
    for i in root:
        if i.tag and i.text:
            if i.tag == 'city':
                city = i.text
            map[i.tag] = i.text
            r.hset(city, i.tag, i.text)


if __name__ == "__main__":
    origin_url = "http://wthrcdn.etouch.cn/WeatherApi?city="
    arg = sys.argv[1]
    #arg = '深圳'
    resp = request_url(origin_url + arg)
    explain_requet(resp)
    print(map)
import csv
import redis

f = open("parking_facilities.csv", "r")
parking_facilities = csv.DictReader(f, delimiter=',')
r = redis.Redis(host='localhost', port=6379, db=0)

def add_parking_facility(license_number, 
        facility_type, 
        entity_name, 
        camis_trade_name,
        address_bldg,
        address_street_name,
        address_location,
        address_city,
        address_state,
        address_zip_code,
        telephone_number,
        number_of_spaces):
    if r.sadd("parking_facilities_set", license_number):
        r.hset("parking_facility:%s" % license_number, "facility_type", facility_type)
        r.hset("parking_facility:%s" % license_number, "entity_name", entity_name)
        r.hset("parking_facility:%s" % license_number, "camis_trade_name", camis_trade_name)
        r.hset("parking_facility:%s" % license_number, "address_bldg", address_bldg)
        r.hset("parking_facility:%s" % license_number, "address_street_name", address_street_name)
        r.hset("parking_facility:%s" % license_number, "address_location", address_location)
        r.hset("parking_facility:%s" % license_number, "address_city", address_city)
        r.hset("parking_facility:%s" % license_number, "address_state", address_state)
        r.hset("parking_facility:%s" % license_number, "address_zip_code", address_zip_code)
        r.hset("parking_facility:%s" % license_number, "telephone_number", telephone_number)
        r.hset("parking_facility:%s" % license_number, "number_of_spaces", number_of_spaces)
        return True
    else:
        return False
        

if __name__ == "__main__":
    for parking_facility_hash in parking_facilities:
        add_parking_facility(parking_facility_hash['License Number'],
            parking_facility_hash['Facility Type'],
            parking_facility_hash['Entity Name'],
            parking_facility_hash['Camis Trade Name'],
            parking_facility_hash['Address Bldg'],
            parking_facility_hash['Address Street Name'],
            parking_facility_hash['Address Location'],
            parking_facility_hash['Address City'],
            parking_facility_hash['Address State'],
            parking_facility_hash['Address Zip Code'],
            parking_facility_hash['Telephone Number'],
            parking_facility_hash['Number of Spaces'])
        print "added parking_facility with %s" % parking_facility_hash['License Number']

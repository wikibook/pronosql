require 'rubygems' #can skip this line in Ruby 1.9
require 'mongo'

field_map = {
    "users" => %w(_id gender age occupation zip_code),
    "movies" => %w(_id title genres),
    "ratings" => %w(user_id movie_id rating timestamp)
}

db = Mongo::Connection.new.db("mydb")
collection_map = {
    "users" => db.collection("users"),
    "movies" => db.collection("movies"),
    "ratings" => db.collection("ratings")
}

unless ARGV.length == 1
    puts "Usage: movielens_dataloader data_filename"
    exit(0)
end

class Array
  def to_h(key_definition)
    result_hash = Hash.new()
    
    counter = 0
    key_definition.each do |definition|
      if not self[counter] == nil then
          if self[counter].is_a? Array or self[counter].is_a? Integer then
              result_hash[definition] = self[counter]
          else
              result_hash[definition] = self[counter].strip
          end
      else
        # Insert the key definition with a empty value.
        # Because we probably still want the hash to contain the key.
        result_hash[definition] = ""
      end
      # For some reason counter.next didn't work here....
      counter = counter + 1
    end
    
    return result_hash
  end
end

if File.exists?(ARGV[0])
    file = File.open(ARGV[0], 'r')
    data_set = ARGV[0].chomp.split(".")[0]
    file.each { |line|
        field_names = field_map[data_set] 
        field_values = line.split("::").map { |item|
            if item.to_i.to_s == item
                item = item.to_i
            else
                item
            end
        }
        puts "field_values: #{field_values}"
        #last_field_value = line.split("::").last
        last_field_value = field_values.last
        puts "last_field_value: #{last_field_value}"
        if last_field_value.split("|").length > 1
           field_values.pop 
           field_values.push(last_field_value.split().join('\n').split("|"))
        end
        field_values_doc = field_values.to_h(field_names)
        collection_map[data_set].insert(field_values_doc)
    }
    puts "inserted #{collection_map[data_set].count()} records into the #{collection_map[data_set].to_s} collection"
end

use XMemeAPIDB

db.createCollection("MemeMaster")

db.createCollection("MemeIDCounter")

db.MemeIDCounter.insert({_id:"Memeid", "sequencevalue":0})
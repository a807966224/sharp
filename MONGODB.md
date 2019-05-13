show dbs
使用Robo 3T创建一个数据库后，openShell后，执行db.随意表名.insert({'随意key':'随意值'})后，此数据库就保存下来了，否则不会保存下来
use db1
db.dropDatabase()
db.stats()
db.version()
db.getMongo()


show collections
db.表名.insert({"k1";"v1"})会自动创建表（集合）
db.createCollection("表名")
创建固定集合 mycol，整个集合空间大小 6142800 KB, 文档最大个数为 10000 个。
db.createCollection("mycol", { capped : true, autoIndexId : true, size : 6142800, max : 10000 } )
db.表名.drop()
db.表名.insertOne({"k1":"v1"})
db.表名.insertMany([{"k1_1":"v1_1"},{"k2":"v2"}])

db.表名.update({"k":"k_v0"},{"k":"k_v0_0"},{multi:false})
db.表名.update({"k":"k1"},{$set:{"k":"k1_1"}},{multi:true})

db.list.remove({"k":"k1_1"})

db.表名.find().pretty()
db.表名.find({"name":"zx"}).pretty()
同类型比较一定要，否则无结果
db.表名.find({"num":{$gt: "30"}})
db.表名.find({"num":{$lte: "35"}})
db.表名.find({"num":{$gt: "35"}})
db.表名.find({"num":{$gte: "35"}})

db.表名.find({"num":"35","name":"???"})
db.表名.find({"name":"???",$or:[{"num":{$gt:"30"}}]})

db.表名.find({"name":"???",$or:[{"num":{$gt:"30"}}]}).limit(2).skip(1)
1正序，-1倒序
db.表名.find({"name":"???",$or:[{"num":{$gt:"30"}}]}).limit(2).skip(1).sort({"num":1})



##### 后续还有很吊的功能，请查看官网吧， 索引，分片，全文检索， 
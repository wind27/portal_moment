package com.wind.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

@Component
public class MongoUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    private static MongoUtil mongoUtil;

    public MongoClient mongoClient;

    private String dbName;

    private String collectionName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    private static Properties prop = new Properties();

    static {
        InputStream in = MongoUtil.class.getClassLoader().getResourceAsStream("server.properties");
        try {
            prop.load(in);
            logger.info("server.properties loading succeed");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MongoUtil() {
        List<ServerAddress> addressList = new ArrayList<>();
        for (int i = 0; i < prop.getProperty("mongo_url").split(",").length; i++) {
            addressList.add(new ServerAddress(prop.getProperty("mongo_url").split(",")[i],
                    Integer.parseInt(prop.getProperty("mongo_port"))));
        }
        mongoClient = new MongoClient(addressList);
    }

    public static MongoUtil getInstance() {
        if (mongoUtil == null) {
            logger.info("new connection to mongo generated at :{}", System.currentTimeMillis());
            mongoUtil = new MongoUtil();
        }
        return mongoUtil;
    }

    public static void main(String[] args) {

        MongoUtil mongoUtil = MongoUtil.getInstance();
        mongoUtil.setDbName("chat");
        mongoUtil.setCollectionName("notice");

        Map<String, Object> params = new HashMap<>();
        params.put("time", new BasicDBObject("$gt", 1446693332));
        params.put("targetUid", 807722);

        System.out.println(mongoUtil.getCount(params));

        //        MongoClient mongoClient = MongoUtil.getInstance().mongoClient;
        //        DB db = mongoClient.getDB("chat");
        //        Set<String> colls = db.getCollectionNames();
        //        for (String s : colls) {
        //            //System.out.println(s);
        //        }
        //        DBCollection feedScoll = db.getCollection("feed");
        //        /*DBCursor cursor = null;
        //        cursor = feedScoll.find();
        //		while(cursor.hasNext()){
        //			DBObject object = cursor.next();
        //			System.out.println(object.get("cover"));
        //		}*/
        //
        //        System.out.println(feedScoll.find(new BasicDBObject("uuid", 2000188)).toArray());
        //
        //        DB db2 = mongoClient.getDB("chat");
        //        DBCollection feedScoll2 = db2.getCollection("feedcomment");
        //
        //        List<DBObject> dbList = feedScoll2.find(new BasicDBObject("tid", 2001465)).toArray();
        //        for (DBObject object : dbList) {
        //            System.out.println(object.get("uid"));
        //        }

    }

    public List<DBObject> query(Map<String, Object> params) {

        MongoClient mongoClient = MongoUtil.getInstance().mongoClient;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbColl = db.getCollection(collectionName);

        DBObject query = new BasicDBObject();
        int start = 0;
        int limit = 0;
        boolean paging = false;
        List<BasicDBObject> sortList = new ArrayList<BasicDBObject>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getKey().equals("sort_query")) {
                sortList.add((BasicDBObject) entry.getValue());
            }
            else if (entry.getKey().equals("paging_start")) {
                start = Integer.parseInt(entry.getValue().toString());
            }
            else if (entry.getKey().equals("paging_limit")) {
                limit = Integer.parseInt(entry.getValue().toString());
                paging = true;
            }
            else {
                query.put(entry.getKey(), entry.getValue());
            }
        }

        DBCursor dbCursor = dbColl.find(query);
        if (dbCursor != null && sortList != null && sortList.size() > 0) {
            for (int i = 0; i < sortList.size(); i++) {
                if (sortList.get(i) != null) {
                    dbCursor = dbCursor.sort(sortList.get(i));
                }
            }
        }
        if (dbCursor != null && paging == true) {
            dbCursor = dbCursor.skip((start - 1) * limit).limit(limit);
        }
        List<DBObject> lists = dbCursor.toArray();

        return lists;
    }

    public void close() {
        this.mongoClient.close();
    }

    public MongoDatabase getDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            return database;
        }
        return null;
    }

    /**
     * 获取collection对象 - 指定Collection
     *
     * @param collName
     * @return
     */
    public MongoCollection<Document> getCollection(String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName)
                .getCollection(collName);
        return collection;
    }

    /**
     * 查询DB下的所有表名
     */
    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
        List<String> _list = new ArrayList<String>();
        for (String s : colls) {
            _list.add(s);
        }
        return _list;
    }

    /**
     * 获取所有数据库名称列表
     *
     * @return
     */
    public MongoIterable<String> getAllDBNames() {
        MongoIterable<String> s = mongoClient.listDatabaseNames();
        return s;
    }

    /**
     * 删除一个数据库
     */
    public void dropDB(String dbName) {
        getDB(dbName).drop();
    }

    /**
     * 查找对象 - 根据主键_id
     *
     * @param
     * @param id
     * @return
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        }
        catch (Exception e) {
            return null;
        }
        Document myDoc = coll.find(Filters.eq("_id", _idobj)).first();
        return myDoc;
    }

    /**
     * 统计数
     */
    public int getCount(MongoCollection<Document> coll) {
        int count = (int) coll.count();
        return count;
    }

    public long getCount(Map<String, Object> params) {
        MongoClient mongoClient = MongoUtil.getInstance().mongoClient;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbColl = db.getCollection(collectionName);

        DBObject query = new BasicDBObject();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.put(entry.getKey(), entry.getValue());
        }
        return dbColl.count(query);
    }

    /**
     * 条件查询
     */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /**
     * 分页查询
     */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo,
            int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize)
                .iterator();
    }

    /**
     * 通过ID删除
     *
     * @param coll
     * @param id
     * @return
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        int count = 0;
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        }
        catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    /**
     *
     *
     * @param coll
     * @param id
     * @param newdoc
     * @return
     */
    public Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        }
        catch (Exception e) {
            return null;
        }
        Bson filter = Filters.eq("_id", _idobj);
        // coll.replaceOne(filter, newdoc); // 完全替代
        coll.updateOne(filter, new Document("$set", newdoc));
        return newdoc;
    }

    public void dropCollection(String dbName, String collName) {
        getDB(dbName).getCollection(collName).drop();
    }

}

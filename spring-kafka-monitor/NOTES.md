# notes

https://www.mongodb.com/docs/manual/reference/method/db.collection.find/#db.collection.find--

```mongosh
db.eventConfirmation.find( { eventId: { $eq: '78be1fde-2f4c-472f-8f1a-d76f5b4071a6' } } );
db.eventConfirmation.getIndexes();
```

```mongosh
cfg = rs.conf();
cfg.members[0].host = "192.168.1.141:27017";
cfg.members[1].host = "192.168.1.142:27017";
cfg.members[2].host = "192.168.1.143:27017";
rs.reconfig(cfg);
```

```mongosh
cfg = rs.conf();
cfg.members[0].host = "192.168.1.149:27017";
rs.reconfig(cfg);
```

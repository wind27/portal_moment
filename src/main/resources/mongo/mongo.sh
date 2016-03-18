
config = {_id: 'wind', members: [{_id: 0, host: '192.168.217.128:20000', priority:10},{_id: 2, host: '192.168.217.128:30000', priority:9}]}

config = {_id: 'wind_shard1', members: [{_id: 0, host: '192.168.217.128:20001', priority:10},{_id: 2, host: '192.168.217.128:30002', priority:9}]}

rs.initiate(config);
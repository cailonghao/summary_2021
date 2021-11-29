docker run -d  -v $PWD/data:/data --name redis -p 6379:6379 redis redis-server --requirepass "123456" --appendonly yes

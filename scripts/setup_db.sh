# Set reasonable defaults, otherwise override with env variables
BLOG_USER=${BLOG_USER:='blog_user'}
BLOG_DB=${BLOG_DB:='clj_blog'}

createuser -d $BLOG_USER
createb -O $BLOG_USER $BLOG_DB

psql $BLOG_DB $BLOG_USER < db/schema.sql

<html>
   <head>
      <title>My First Coherence Cache</title>
   </head>
   <body>
      <h1>
         <%@ page language="java"
                  import="com.tangosol.net.CacheFactory,
                          com.tangosol.net.NamedCache"
         %>
         <%
            String key = "k2";
            String value = "Hello World!";

            CacheFactory.ensureCluster();
            NamedCache cache = CacheFactory.getCache("hello-example");

            cache.put(key, value);
            out.println((String)cache.get(key));

            CacheFactory.shutdown();
         %>
      </h1>
   </body>
</html>
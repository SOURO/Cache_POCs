/**
 * 
 */
package com.souro;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * @author sourabrata
 *
 */
public class CacheCoherenceDemo {
	public static void main(String[] args) {
		 
	      String key = "k2";
	      String value = "Hello World!";
	 
	      CacheFactory.ensureCluster();
	      NamedCache cache = CacheFactory.getCache("hello-example");
	      /*NamedCache cache = CacheFactory.getCache("test");*/
	 
	      cache.put(key, value);
	      System.out.println((String)cache.get(key));
	 
	      CacheFactory.shutdown();
	   }
}

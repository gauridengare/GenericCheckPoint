package genericCheckpointing.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class ProxyCreator {

  public StoreRestoreI createProxy(Class<?>[] c,InvocationHandler handler)
  {
	  StoreRestoreI storeRestoreRef =
	            (StoreRestoreI)
	            Proxy.newProxyInstance(
	                                   getClass().getClassLoader(),
	                                   c,
	                                   handler
	                                   );
	  return storeRestoreRef;

  }
}

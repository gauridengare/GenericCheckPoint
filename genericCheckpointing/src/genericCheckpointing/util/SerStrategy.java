package genericCheckpointing.util;

import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public interface SerStrategy {
	SerializableObject processInput(SerializableObject sObject);
}

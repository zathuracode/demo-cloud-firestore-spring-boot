package com.vobi.demo.cloud.firestore.listener;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QuerySnapshot;

@Component
public class FirebaseSpringBoorListenerImpl implements FirebaseSpringBoorListener {
	
	private final static Logger log=LoggerFactory.getLogger(FirebaseSpringBoorListenerImpl.class);
	
	@Autowired
	Firestore db;
	
	private ListenerRegistration registration=null;
	
	@PostConstruct
	public void initFirebase() {
		log.info("initFirebase Listener");
		registration= db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
	      @Override
	      public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirestoreException e) {
	        
	    	if (e != null) {
	    		 log.error("Listen failed: " + e);
	    		 return;
	        }

	        for (DocumentChange dc : snapshots.getDocumentChanges()) {
	          switch (dc.getType()) {
	            case ADDED:
	            	log.info("New user: " + dc.getDocument().getId());
	            	log.info("New user: " + dc.getDocument().getData());
	              break;
	            case MODIFIED:
	            	log.info("Modified user: " + dc.getDocument().getId());
	            	log.info("Modified user: " + dc.getDocument().getData());
	              break;
	            case REMOVED:
	            	log.info("Removed user: " + dc.getDocument().getId());
	            	log.info("Removed user: " + dc.getDocument().getData());
	              break;
	            default:
	              break;
	          }
	        }
	      }
	    });
	}
	
	@PreDestroy
	public void destroy() {
		if(registration!=null) {
			log.info("destroy Listener");
			registration.remove();
		}
	}
	 
	 

}

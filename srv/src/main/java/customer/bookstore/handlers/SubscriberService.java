package customer.bookstore.handlers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sap.cds.Result;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import cds.gen.subscriberservice.Subscriber;
import cds.gen.subscriberservice.SubscriberService_;
import cds.gen.subscriberservice.Subscriber_;

@Component
@ServiceName(SubscriberService_.CDS_NAME)
public class SubscriberService implements EventHandler {
    
    @Autowired
    PersistenceService db;
    private Map<Object, Map<String, Object>> subs = new HashMap<>();
    // @On(event = CqnService.EVENT_READ, entity = Subscriber_.CDS_NAME)
    // public void onRead(CdsReadEventContext context){
    //     CqnSelect subss = Select.from(Subscriber_.class);
    //  Subscriber subscribers = db.run(subss).first(Subscriber.class).orElse(null);
        
    //     System.out.println(subscribers);
    //     Result values = db.run(context.getCqn());
        
    //     System.out.println(values);
    //     context.setResult(subs.values());
        
    // }
}

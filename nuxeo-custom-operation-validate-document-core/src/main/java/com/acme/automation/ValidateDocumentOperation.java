package com.acme.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.NuxeoException;

/**
 *
 */
@Operation(id=ValidateDocumentOperation.ID, category=Constants.CAT_DOCUMENT, label="Document.CustomValidate", description="Describe here what your operation does.")
public class ValidateDocumentOperation {

    public static final String ID = "Document.CustomValidate";

    private static final String TEST_WORKSPACE_PATH = "/default-domain/workspaces/test-ws/";
    
    private static final Logger log = LogManager.getLogger(ValidateDocumentOperation.class);

    @Context
    protected CoreSession session;

    @OperationMethod
    public DocumentModel run(DocumentModel doc) {
        log.warn("<run> " + doc.getPathAsString());
        if (doc.getPathAsString().startsWith(TEST_WORKSPACE_PATH)) {
            log.error("Validation error " + doc.getPathAsString());
            throw new NuxeoException("Oups!");
        }
        return doc;
    }
}

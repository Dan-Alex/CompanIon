package com.alexdan.docflow.data;

import com.alexdan.docflow.models.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, String> {
}

package com.alexdan.companion.data;

import com.alexdan.companion.models.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, String> {
}

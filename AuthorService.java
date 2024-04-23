import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.CpfValidator;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
    }

    public Author updateAuthor(Long id, AuthorDTO authorDTO) {
        Author existingAuthor = getAuthorById(id);
        existingAuthor.setName(authorDTO.getName());
        existingAuthor.setEmail(authorDTO.getEmail());
        existingAuthor.setDescription(authorDTO.getDescription());
        existingAuthor.setCpf(authorDTO.getCpf());
        return authorRepository.save(existingAuthor);
    }

    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }

    public Author createAuthor(@Valid AuthorDTO authorDTO) {
        if (!isValidCPF(authorDTO.getCpf())) {
            throw new InvalidCPFException("CPF inválido");
        }

        Author author = new Author(authorDTO.getName(), authorDTO.getEmail(), authorDTO.getDescription(), authorDTO.getCpf());
        return authorRepository.save(author);
    }

    private boolean isValidCPF(String cpf) {
        CpfValidator validator = new CpfValidator();
        return validator.isValid(cpf);
    }
}

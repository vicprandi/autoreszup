import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AuthorDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 120, message = "Name must be less than 120 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Size(max = 120, message = "Email must be less than 120 characters")
    private String email;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 2500, message = "Description must be less than 2500 characters")
    private String description;

    @NotBlank(message = "CPF is mandatory")
    @Pattern(regexp = "^[0-9]{11}$", message = "Invalid CPF format")
    private String cpf;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

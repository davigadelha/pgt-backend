package br.gov.serpro.pgt.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.serpro.pgt.backend.web.rest.TestUtil;

public class FuncionalidadeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Funcionalidade.class);
        Funcionalidade funcionalidade1 = new Funcionalidade();
        funcionalidade1.setId(1L);
        Funcionalidade funcionalidade2 = new Funcionalidade();
        funcionalidade2.setId(funcionalidade1.getId());
        assertThat(funcionalidade1).isEqualTo(funcionalidade2);
        funcionalidade2.setId(2L);
        assertThat(funcionalidade1).isNotEqualTo(funcionalidade2);
        funcionalidade1.setId(null);
        assertThat(funcionalidade1).isNotEqualTo(funcionalidade2);
    }
}

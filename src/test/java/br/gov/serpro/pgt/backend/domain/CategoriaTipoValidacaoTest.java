package br.gov.serpro.pgt.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.serpro.pgt.backend.web.rest.TestUtil;

public class CategoriaTipoValidacaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoriaTipoValidacao.class);
        CategoriaTipoValidacao categoriaTipoValidacao1 = new CategoriaTipoValidacao();
        categoriaTipoValidacao1.setId(1L);
        CategoriaTipoValidacao categoriaTipoValidacao2 = new CategoriaTipoValidacao();
        categoriaTipoValidacao2.setId(categoriaTipoValidacao1.getId());
        assertThat(categoriaTipoValidacao1).isEqualTo(categoriaTipoValidacao2);
        categoriaTipoValidacao2.setId(2L);
        assertThat(categoriaTipoValidacao1).isNotEqualTo(categoriaTipoValidacao2);
        categoriaTipoValidacao1.setId(null);
        assertThat(categoriaTipoValidacao1).isNotEqualTo(categoriaTipoValidacao2);
    }
}

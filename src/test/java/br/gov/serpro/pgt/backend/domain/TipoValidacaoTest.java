package br.gov.serpro.pgt.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.serpro.pgt.backend.web.rest.TestUtil;

public class TipoValidacaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoValidacao.class);
        TipoValidacao tipoValidacao1 = new TipoValidacao();
        tipoValidacao1.setId(1L);
        TipoValidacao tipoValidacao2 = new TipoValidacao();
        tipoValidacao2.setId(tipoValidacao1.getId());
        assertThat(tipoValidacao1).isEqualTo(tipoValidacao2);
        tipoValidacao2.setId(2L);
        assertThat(tipoValidacao1).isNotEqualTo(tipoValidacao2);
        tipoValidacao1.setId(null);
        assertThat(tipoValidacao1).isNotEqualTo(tipoValidacao2);
    }
}

package br.gov.serpro.pgt.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.serpro.pgt.backend.web.rest.TestUtil;

public class ResultadoValidacaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResultadoValidacao.class);
        ResultadoValidacao resultadoValidacao1 = new ResultadoValidacao();
        resultadoValidacao1.setId(1L);
        ResultadoValidacao resultadoValidacao2 = new ResultadoValidacao();
        resultadoValidacao2.setId(resultadoValidacao1.getId());
        assertThat(resultadoValidacao1).isEqualTo(resultadoValidacao2);
        resultadoValidacao2.setId(2L);
        assertThat(resultadoValidacao1).isNotEqualTo(resultadoValidacao2);
        resultadoValidacao1.setId(null);
        assertThat(resultadoValidacao1).isNotEqualTo(resultadoValidacao2);
    }
}

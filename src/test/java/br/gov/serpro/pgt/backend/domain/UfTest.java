package br.gov.serpro.pgt.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.serpro.pgt.backend.web.rest.TestUtil;

public class UfTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Uf.class);
        Uf uf1 = new Uf();
        uf1.setId(1L);
        Uf uf2 = new Uf();
        uf2.setId(uf1.getId());
        assertThat(uf1).isEqualTo(uf2);
        uf2.setId(2L);
        assertThat(uf1).isNotEqualTo(uf2);
        uf1.setId(null);
        assertThat(uf1).isNotEqualTo(uf2);
    }
}

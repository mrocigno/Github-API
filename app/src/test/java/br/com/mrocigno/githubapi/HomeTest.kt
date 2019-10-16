package br.com.mrocigno.githubapi

import br.com.mrocigno.githubapi.model.Github
import br.com.mrocigno.githubapi.model.GithubOwner
import br.com.mrocigno.githubapi.presenter.HomePresenterImpl
import br.com.mrocigno.githubapi.view.HomeActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeTest {

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testValidate_needReturnFalse(){
        val presenter = HomePresenterImpl(Mockito.mock(HomeActivity::class.java))

        val list = listOf(
            Github(
                placeholder = true,
                description = ""
            ),
            Github(
                placeholder = false,
                forks_count = 123,
                description = "A mockito test",
                full_name = "Mockito-test",
                html_url = "https://www.github.com.br/",
                infoCard = false,
                name = "Mockito",
                owner = GithubOwner(
                    id = 10,
                    avatar_url = "https://www.github.com.br/",
                    login = "Matheus",
                    url = "https://www.github.com.br/mrocigno"
                ),
                stargazers_count = 1000
            )
        )

        Assert.assertFalse(presenter.valid(list))
    }

    @Test
    fun testValidate_needReturnTrue(){
        val presenter = HomePresenterImpl(Mockito.mock(HomeActivity::class.java))

        val list = listOf(
            Github(
                placeholder = false,
                forks_count = 123,
                description = "A mockito test",
                full_name = "Mockito-test",
                html_url = "https://www.github.com.br/",
                infoCard = false,
                name = "Mockito",
                owner = GithubOwner(
                    id = 10,
                    avatar_url = "https://www.github.com.br/",
                    login = "Matheus",
                    url = "https://www.github.com.br/mrocigno"
                ),
                stargazers_count = 1000
            )
        )

        Assert.assertTrue(presenter.valid(list))
    }

}
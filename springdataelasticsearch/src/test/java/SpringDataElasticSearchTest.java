import com.itheima.es.entity.Article;
import com.itheima.es.repositories.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class SpringDataElasticSearchTest {
    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private ArticleRepository articleRepository;
    //建立索引
    @Test
    public void createTest() throws Exception{
        template.createIndex(Article.class);
    }

    //往索引库中添加文档
    @Test
    public void addDocument(){
        Article article = new Article();
        article.setId(1);
        article.setTitle("'煞笔'是什么意思？");
        article.setContent("聊天里的：我说煞笔骂谁，你说煞笔骂我。这个纯属于你们在玩文字游戏，让他抓到了吧，呵呵。煞笔是个贬义词。有网络通用词，骂人用语。即：傻逼~");
        articleRepository.save(article);
    }

    //循环往索引库中添加多条数据
    @Test
    public void addMoreDocument(){
        for (int i = 1; i <= 20; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle("'煞笔'是什么意思？" + i);
            article.setContent("聊天里的：我说煞笔骂谁，你说煞笔骂我。这个纯属于你们在玩文字游戏，让他抓到了吧，呵呵。煞笔是个贬义词。有网络通用词，骂人用语。即：傻逼~" + i);
            articleRepository.save(article);
        }
    }
    //在索引库中根据id删除一条数据
    @Test
    public void deleteDocumentById(){
        articleRepository.deleteById(20l);
    }
    //deleteAll
    @Test
    public void deleteAll(){
        articleRepository.deleteAll();
    }

    //查询所有的document数据
    @Test
    public void findAll(){
        articleRepository.findAll().forEach(article -> System.out.println(article));
    }

    //findAllById
    @Test
    public void findById(){
        Optional<Article> article = articleRepository.findById(20l);
        System.out.println(article);
    }
    //findByTitle
    @Test
    public void findByTitle(){
        articleRepository.findByTitle("煞笔").forEach(article -> System.out.println(article));
    }

    //findByTitleOrContent
    @Test
    public void findByTitleOrContent() throws Exception{
        Pageable pageable = PageRequest.of(1,5);
        articleRepository.findByTitleOrContent("傻逼","傻逼", pageable).forEach(article -> System.out.println(article));
    }
}

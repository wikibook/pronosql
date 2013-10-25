import org.springframework.data.keyvalue.redis.core.RedisTemplate;

public class TagSynonymsDao {

  private RedisTemplate<String, String> template;

  public TagSynonymsDao(RedisTemplate template) {
    this.template = template;
  }

  public Long addSynonymTag(String keyTag, String synonymTag) {
    Long index = template.opsForList().rightPush(keyTag, synonymTag);
    return index;
  }
  
  public List getAllSynonymTags(String keyTag) {
    List<String> synonymTags = template.opsForList().range(word, 0, -1);
    return synonymTags;
    }
    
    public void removeSynonymTags(String... synonymTags) {
    template.delete(Arrays.asList(synonymTags));
}

}

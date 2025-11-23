一个数据结构的课程设计
主要功能包括
mode1：识别命令并作出回应
      命令现有：cal - 显示当天的日期，表现与linux系统的cal命令大致相同
               news - 从https://github.com/vikiboss/60s.git中获取精选的15条新闻
      后续可能增加新的命令
mode2：使用NLP工具识别意图并根据意图匹配预设的回答
      包含Stanford CoreNLP、OpenNLP、NLTK
mode3：引入阿里云百炼大模型平台的LLM来生成回答，支持多轮对话，项目开放期间会将apikey写在代码里供用户体验
       项目停止开放后该仓库可见性会调回private，同时用于该项目的apikey也会被删除

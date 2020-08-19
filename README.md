Ce projet illustre une partie de l'article <REX Spring batch>. 
Ce que fait le batch n'a pas réellement d'importance, seule la configuration autour a de l'importance.

Prérequis :
- Connaitre le lien job, step, reader, processor, writer
- Connaître les jobParameters, jobExecutionContext, StepExecutionContext
- Savoir ajouter un Listener au job
- Connaitre la notion de chunk

Notions abordées :
- validator pour valider les paramètres en entrée du batch
- incrémentor pour s'assurer de ne pas lancer un batch en cours d'éxécution
- utilisation du parametre dryrun dans le writer pour lancer le batch "à blanc"
- utilisation de javax.validation dans le processor pour valider les données avant de les traiter (les stocker en base le cas échéant)
- utilisation du skip (dans quelle condition ?)
- mise en place d'un listener onError pour catcher les runtimeException et avertir l'utilisateur

import {
  faHeart,
  faHouse,
  faPen,
  faSearch,
  faUser,
} from '@fortawesome/free-solid-svg-icons'

import FooterLinkWithIcon from './FooterLinkWithIcon'

import FlexBox from '@component/layout/FlexBox'

import { Container } from '@styled/component/layout/Footer'

export default function Footer() {
  return (
    <Container>
      <FlexBox j="space-around" a="center" w="100%" h="100%">
        <FooterLinkWithIcon icon={faHouse} to="/" title="홈" />
        <FooterLinkWithIcon icon={faSearch} to="/home" title="검색" />
        <FooterLinkWithIcon icon={faHeart} to="/myCourse" title="나의 코스" />
        <FooterLinkWithIcon icon={faPen} to="/favorite" title="북마크" />
        <FooterLinkWithIcon icon={faUser} to="/myPage" title="마이페이지" />
      </FlexBox>
    </Container>
  )
}
